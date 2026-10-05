       
       // for Generate Secret Code
       ============================
       
        SecretKey key = Jwts.SIG.HS256.key().build();

        String secret = java.util.Base64
                .getEncoder()
                .encodeToString(key.getEncoded());

        System.out.println(secret);