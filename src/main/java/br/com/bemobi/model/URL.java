package br.com.bemobi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by diego.magalhaes on 3/23/2016.
 * Transport for the shorten operations
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class URL {
    @Id @GeneratedValue @JsonIgnore private Long id;
    @JsonProperty("alias") private String alias;
    @JsonProperty("url") private String url;
    @JsonProperty("err_code") private String errorCode;
    @JsonProperty("description") private String description;
    @Transient @JsonProperty("statistics") private Statistics statistics;
}
