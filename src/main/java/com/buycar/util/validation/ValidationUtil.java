package com.buycar.util.validation;

import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class ValidationUtil {

    public static <T> T checkNotFoundWithId(Optional<T> object, Long id) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        checkNotFoundWithId(object.isPresent(), id);
        return object.get();
    }

    public static void checkNotFoundWithId(boolean found, Long id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found entity with " + msg);
        }
    }
}
