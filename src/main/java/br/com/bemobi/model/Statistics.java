package br.com.bemobi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * Created by diego.magalhaes on 3/22/2016.
 * Represents the statistics from the ops ocurred in the controller
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {
    @JsonProperty("time_taken") private long timeTaken;
    @JsonProperty("unit") private TimeUnit unit;
}
