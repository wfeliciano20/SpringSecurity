package com.smoothstack.authentication.utility;

import com.auth0.jwt.algorithms.Algorithm;

public class Utility {
    public static Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
}
