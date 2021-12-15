package me.laus.tallinn.transport.model.request;

import me.laus.tallinn.transport.model.SiriStop;

import java.time.Instant;
import java.util.ArrayList;

public abstract class SiriRequest extends ExternalApiRequest {
    public static class Parameters extends ArrayList<Parameter> {
        private Parameters() {}

        public static Builder newBuilder() {
            return new Parameters().new Builder();
        }

        public class Builder {
            private Builder() {}

            public Builder setStop(SiriStop stop) {
                Parameters.this.add(new ExternalApiRequest.Parameter("stopid", stop.getSiriId()));
                return this;
            }

            public Builder setTime(Instant instant) {
                Parameters.this.add(new ExternalApiRequest.Parameter("time", String.valueOf(instant.getEpochSecond())));
                return this;
            }

            public Builder setTime() {
                Instant instant = Instant.now();
                setTime(instant);
                return this;
            }

            public Parameters build() {
                return Parameters.this;
            }
        }
    }
}
