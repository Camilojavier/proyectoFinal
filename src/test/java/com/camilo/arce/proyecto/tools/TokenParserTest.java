package com.camilo.arce.proyecto.tools;

import com.camilo.arce.proyecto.dto.OpenIDResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TokenParserTest {
    public String getTestResponse(){
        return "\n" +
                "{\n" +
                "  \"access_token\": \"ya29.a0Ad52N399Wi6END-WtKVhRZ71Iqw2FrX748pTbP3EHy611e56-6bA_OYi4aavgW5qwwjAoStl4KfRaaCaXLQEJhm4UnjsqqBpIKpzZM1etp0yLnLO_zG-IZeG3hOGSghh10E4EEchwezNyVlWshHAC-Vkb8P0lvQoK4AaaCgYKATISARASFQHGX2MidTY7lhdSDF32DXGy93RwyQ0171\",\n" +
                "  \"expires_in\": 3599,\n" +
                "  \"scope\": \"https://www.googleapis.com/auth/userinfo.email openid\",\n" +
                "  \"token_type\": \"Bearer\",\n" +
                "  \"id_token\": \"eyJhbGciOiJSUzI1NiIsImtpZCI6ImUxYjkzYzY0MDE0NGI4NGJkMDViZjI5NmQ2NzI2MmI2YmM2MWE0ODciLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIyODk0MzM0MjQ1NTQtYWwzOG01bm41aDZja3BhZHQ1MWthbHAwOGRyM2dtZnAuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIyODk0MzM0MjQ1NTQtYWwzOG01bm41aDZja3BhZHQ1MWthbHAwOGRyM2dtZnAuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQ3OTA4MTE2NjU5MjgzNDg2NDciLCJoZCI6ImVzdC51bXNzLmVkdSIsImVtYWlsIjoiMjAxODAwNDg5QGVzdC51bXNzLmVkdSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoidUVKODRjU1A2RmE5VVdNLUREQllMUSIsIm5vbmNlIjoibm9uY2UiLCJpYXQiOjE3MTQ2MDAwNTcsImV4cCI6MTcxNDYwMzY1N30.ix_IoA9Q101xusu_k_I25eVGRWG191wCVUqgNQdZq_ux8_GnvJlxUyG5VVWeZugaOWCnJPccoD8RIr8AKRGP7Z3Qk5i1qLYhT3gLlRkFXY1zEATvtA6aj9cBr-T8MFMT8WpZI694BqF7-uyoVKqtjBSH93TdNE3GAXEoUp8SIzgR5nN709KxNxUR2sx1ILqCcWh-X9SVv2fyIm6AufPpX_huFjlNrGQhqVt35XRU4qnjH837WjOsV5idM9GmAU3YHBQ4flyxIy6FCCbg044WhPz-f860fxpfFZ-PTF93hRtUR4X3TJPhS3XqnJTqEWK1tgCDQc76gC67YbusfufnDQ\"\n" +
                "}";
    }

    @Test
    public void testTokenParser(){
        OpenIDResponseDto responseDTO = TokenParser.parseResponse(getTestResponse());
        assert responseDTO != null;
        assertNotNull(responseDTO.getAccessToken());
        assertNotNull(responseDTO.getExpiresIn());
        assertNotNull(responseDTO.getScope());
        assertNotNull(responseDTO.getTokenType());
        assertNotNull(responseDTO.getIdToken());
        assertEquals("ya29.a0Ad52N399Wi6END-WtKVhRZ71Iqw2FrX748pTbP3EHy611e56-6bA_OYi4aavgW5qwwjAoStl4KfRaaCaXLQEJhm4UnjsqqBpIKpzZM1etp0yLnLO_zG-IZeG3hOGSghh10E4EEchwezNyVlWshHAC-Vkb8P0lvQoK4AaaCgYKATISARASFQHGX2MidTY7lhdSDF32DXGy93RwyQ0171", responseDTO.getAccessToken());
        assertEquals("3599", responseDTO.getExpiresIn());
        assertEquals("https://www.googleapis.com/auth/userinfo.email openid", responseDTO.getScope());
        assertEquals("Bearer", responseDTO.getTokenType());
        assertEquals("eyJhbGciOiJSUzI1NiIsImtpZCI6ImUxYjkzYzY0MDE0NGI4NGJkMDViZjI5NmQ2NzI2MmI2YmM2MWE0ODciLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIyODk0MzM0MjQ1NTQtYWwzOG01bm41aDZja3BhZHQ1MWthbHAwOGRyM2dtZnAuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIyODk0MzM0MjQ1NTQtYWwzOG01bm41aDZja3BhZHQ1MWthbHAwOGRyM2dtZnAuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQ3OTA4MTE2NjU5MjgzNDg2NDciLCJoZCI6ImVzdC51bXNzLmVkdSIsImVtYWlsIjoiMjAxODAwNDg5QGVzdC51bXNzLmVkdSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoidUVKODRjU1A2RmE5VVdNLUREQllMUSIsIm5vbmNlIjoibm9uY2UiLCJpYXQiOjE3MTQ2MDAwNTcsImV4cCI6MTcxNDYwMzY1N30.ix_IoA9Q101xusu_k_I25eVGRWG191wCVUqgNQdZq_ux8_GnvJlxUyG5VVWeZugaOWCnJPccoD8RIr8AKRGP7Z3Qk5i1qLYhT3gLlRkFXY1zEATvtA6aj9cBr-T8MFMT8WpZI694BqF7-uyoVKqtjBSH93TdNE3GAXEoUp8SIzgR5nN709KxNxUR2sx1ILqCcWh-X9SVv2fyIm6AufPpX_huFjlNrGQhqVt35XRU4qnjH837WjOsV5idM9GmAU3YHBQ4flyxIy6FCCbg044WhPz-f860fxpfFZ-PTF93hRtUR4X3TJPhS3XqnJTqEWK1tgCDQc76gC67YbusfufnDQ", responseDTO.getIdToken());
    }
}
