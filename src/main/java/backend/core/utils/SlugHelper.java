package backend.core.utils;

import backend.core.utils.exceptions.SlugHelperException;

import java.text.Normalizer;

public class SlugHelper {

    public static String toSlug(String value) {

        if (value == null || value.trim().isEmpty() || value.trim().isBlank()){
            throw new SlugHelperException("Slug value error.");
        }
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD);
        return normalized.toLowerCase()
                .replaceAll("ı", "i") //? :D
                .replaceAll("[^\\p{ASCII}]", "") // remove non-ASCII chars
                .replaceAll("[^a-z0-9\\s-]", "") // remove non-alphanumeric chars except -
                .replaceAll("\\s+", "-") // replace whitespace with -
                .replaceAll("-{2,}", "-") // replace consecutive - with single -
                .replaceAll("^-|-$", "");

    }

}
