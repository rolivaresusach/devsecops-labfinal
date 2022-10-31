package org.soyphea.k8s.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "user")
@Component
@Data
public class ConfigMapPodInjectionProperties {

    String name;

    String blog;
}
