package learn.web;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FrameworkEndpoint
public class JwkSetEndpoint {

    @Autowired
    private JWKSet jwkSet;

    /**
     * 获取public key
     * @return
     */
	@GetMapping("/.well-known/jwks.json")
	@ResponseBody
	public Map<String, Object> getKey() {
		return this.jwkSet.toJSONObject();
	}
}