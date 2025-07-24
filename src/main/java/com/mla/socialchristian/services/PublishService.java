package com.mla.socialchristian.services;

import com.mla.socialchristian.domain.DTOs.inputmodels.AlterPublishDetailInputModel;
import com.mla.socialchristian.domain.DTOs.inputmodels.PublishDetailInputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.PublishDetailOutputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.ResultViewModel;
import com.mla.socialchristian.domain.entities.LikePublishEntity;
import com.mla.socialchristian.domain.entities.PublishEntity;
import com.mla.socialchristian.domain.interfaces.repository.IPublishRepository;
import com.mla.socialchristian.domain.interfaces.services.IPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishService implements IPublishService {

    @Autowired
    private IPublishRepository publishRepository;

    @Override
    public ResultViewModel<List<PublishDetailOutputModel>> get(Integer idUser, boolean getAll) {
        var result = new ResultViewModel<List<PublishDetailOutputModel>>();

        var publishEntities = getAll
                ? publishRepository.findAll()
                : publishRepository.findByIdAccount(idUser);

        publishEntities.sort((a, b) -> b.getId().compareTo(a.getId()));

        var output = publishEntities.stream().map(p -> new PublishDetailOutputModel(
                p.getId(),
                p.getBookReference(),
                p.getChapterMessage(),
                p.getTitle(),
                p.getConclusion(),
                p.getLikes().size(),
                p.getLikes().stream().anyMatch(l -> l.getIdAccount().equals(idUser)),
                p.getIdAccount().equals(idUser)
        ))
        .toList();

        return result.addResult(output);
    }

    @Override
    public ResultViewModel<PublishDetailOutputModel> create(Integer idUser, PublishDetailInputModel input) {
        var publish = new PublishEntity(input, idUser);

        publishRepository.saveAndFlush(publish);

        var output = new PublishDetailOutputModel(
            publish.getId(),
            publish.getBookReference(),
            publish.getChapterMessage(),
            publish.getTitle(),
            publish.getConclusion(),
            0,
            false,
            true
        );

        return new ResultViewModel<>(output);
    }

    @Override
    public ResultViewModel<PublishDetailOutputModel> alter(Integer idUser, AlterPublishDetailInputModel input) {
        var publish = publishRepository.findByIdAndIdAccount(input.Id, idUser).orElseThrow();

        publish.setBookReference(input.bookReference);
        publish.setChapterMessage(input.chapterMessage);
        publish.setTitle(input.title);
        publish.setConclusion(input.conclusion);

        publishRepository.saveAndFlush(publish);

        var output = new PublishDetailOutputModel(
                publish.getId(),
                publish.getBookReference(),
                publish.getChapterMessage(),
                publish.getTitle(),
                publish.getConclusion(),
                publish.getLikes().size(),
                publish.getLikes().stream().anyMatch(l -> l.getIdAccount().equals(idUser)),
                true
        );

        return new ResultViewModel<>(output);
    }

    @Override
    public ResultViewModel<Boolean> delete(Integer idUser, Integer id) {
        var publish = publishRepository.findByIdAndIdAccount(id,idUser);
        publish.ifPresent(publishEntity -> publishRepository.delete(publishEntity));

        var result = new ResultViewModel<>(false);
        return publish.isPresent()
                ? result.addResult(true)
                : result.addErrors("Ocorreu um erro ao remover publicação");
    }

    @Override
    public ResultViewModel<Boolean> like(Integer idUser, Integer id) {
        var publish = publishRepository.findById(id).orElseThrow();

        var like = publish.getLikes()
                .stream().filter(l -> l.getIdAccount().equals(idUser))
                .findFirst()
                .orElse(new LikePublishEntity(id, idUser));

        if (like.getId() != null) publish.removeLike(like);
        else publish.addLike(like);

        publishRepository.saveAndFlush(publish);

        return new ResultViewModel<>(true);
    }
}
