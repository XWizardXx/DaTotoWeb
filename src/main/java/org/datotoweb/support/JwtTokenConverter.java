package org.datotoweb.support;

import ch.qos.logback.core.rolling.helper.TokenConverter;
import jakarta.persistence.Convert;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenConverter implements Convert<Jwt>
{

}
