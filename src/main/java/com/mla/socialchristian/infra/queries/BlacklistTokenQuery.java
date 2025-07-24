package com.mla.socialchristian.infra.queries;

public final class BlacklistTokenQuery {
    public static final String INSERT_TOKEN = "INSERT INTO blacklisttoken (token, timestamp) SELECT :token, :timeStampToken WHERE NOT EXISTS (SELECT 1 FROM blacklisttoken WHERE timestamp < :timeStampNow)";
    public static final String UPDATE_TOKEN = "UPDATE blacklisttoken SET token = :token, timestamp = :timeStampToken WHERE timestamp < :timeStampNow";
}
