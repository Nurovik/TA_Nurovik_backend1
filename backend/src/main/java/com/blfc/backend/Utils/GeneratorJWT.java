package com.blfc.backend.Utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GeneratorJWT {
// fungsi generate token
//ini fungsi membuat token


    public static String createToken(String id) {
        //
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                // Set Header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                //set payload or claim
                .setId(id)
                .setIssuedAt(now)
                .setSubject("permintaanbahan")
                .setIssuer("PT.SUMBERBAROKAH")
//                  .setExpiration(new Date(System.currentTimeMillis()+1*60*1000))// 1 menit, 60 * 60 * 1000 = 30 menit
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) //24 Jam, 60*60*2*1000 = 2 jam
                .signWith(SignatureAlgorithm.HS256, "sumberbarokah");

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /*public static Claims validateToken(String token){
    	Claims claims = null;
    	try {
    		 claims = Jwts.parser()
                     	.setSigningKey("JWTPurchaseOrderSecret")
                     		.parseClaimsJws(token)
                     			.getBody();
		} catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
			return claims;
		}
       
        return claims;
    }
     */
    public static Claims validateToken(String token) {
        Claims claims = null;

        claims = Jwts.parser()
                .setSigningKey("sumberbarokah")
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public static Map<String, Object> checkToken(String token, String username) {

        Map<String, Object> response = new HashMap<String, Object>();

        // Start Check Token
        try {

            if (CheckUtil.isNullOrEmpty(token)) {

                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "Token tidak boleh kosong");
                return response;

            } else if (CheckUtil.isNullOrEmpty(username)) {

                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "Username tidak boleh kosong");
                return response;
            }

            Claims claims = validateToken(token);

            if (!claims.getId().equals(username)) {

                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "Bukan Token dengan username :" + username);
                return response;
            }

        } catch (ExpiredJwtException expired) {

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "Token Expired");
            return response;

        } catch (SignatureException signature) {

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "Token Failed");
            return response;

        } catch (Exception e) {

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "Token Failed");
            return response;
        }

        response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
        response.put(Constants.STATUS, Constants.SUCCESS);

        return response;
        // End Check Token

    }

}
