# Rede Social Cristã

### Objetivo
Para praticar tudo oque foi ensina, me desafiei a criar uma api que simula uma rede social.
O intuito é ter o básico da rede, como:
- Criar usuário;
- Ver, alterar e excluir perfil;
- Criar, alterar e excluir uma publicação;

### Desafio
- Utilizar um Spring Boot LTS mais recente - 3.5.4;
- Integrar com banco SQLite;
- Interceptar exceções;
- Validar entradas;
- Integrar JPA;
- Autenticação JWT;
- Blacklist de tokens;

Foram necessaries algumas horas de estudos, pois seguindo o material das aulas aconteceram alguns erros, devido à versão mais atualizada do **spring boot**.
Trabalhei com injeção de dependências, o que tenho uma leve familiaridade, pois trabalho com C# atualmente.
A maior dificuldade é integrações SQLite, pois as documentações que achei são muito antigas, tive que pegar um pouco de cada para implementar, e ia ajustando os erros passo a passo até deixar rodando lisinho.