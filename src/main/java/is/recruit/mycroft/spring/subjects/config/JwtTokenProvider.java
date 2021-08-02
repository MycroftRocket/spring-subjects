package is.recruit.mycroft.spring.subjects.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import is.recruit.mycroft.spring.subjects.model.user.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	@Value("${mycroft.subjects.jwt.secret}")
	private String secretKey;

	private long tokenValidMillisecond = 7 * (1000 * 60 * 60 * 24);

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createdToken(String userPk, Set<Role> set) {
		Claims claims = Jwts.claims().setSubject(userPk);
		claims.put("roles", set);
		Date now = new Date();
		Date expire = new Date(now.getTime() + tokenValidMillisecond);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expire)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
}
