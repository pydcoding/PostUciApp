package com.pyd.postuciapp.utils;

import org.jetbrains.annotations.NotNull;

public class Utils {

    private static final int MIN_PASSWORD_LENGTH = 6;

    /**
     * Metodo que comprueba si un string contiene solo letras.
     *
     * @param query: string a comprobar.
     * @return true si el string es correcto.
     */
    private static boolean isQueryOk(@NotNull final String query) {
        final char[] chars = query.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidDni(@NotNull final String dni) {
        return (dni.matches("\\d\\d\\d\\d\\d\\d\\d\\d[a-zA-Z]"));
    }

    /**
     * Metodo que comprueba si la contraseña es correcta.
     *
     * @param password: contraseña a comprobar.
     * @return true si la contraseña es correcta.
     */
    public static boolean isValidPassword(@NotNull final String password) {
        return (!password.trim().isEmpty() &&
                !(password.trim().length() < MIN_PASSWORD_LENGTH) &&
                !(password.toUpperCase().contains("SELECT")) && !(password.toUpperCase().contains("DROP")) &&
                !(password.toUpperCase().contains("DELETE")) && !(password.toUpperCase().contains("UPDATE")) &&
                !(password.contains("*")) && !(password.contains("/")) && !(password.contains("\\")) &&
                !(password.contains("=")) && !(password.contains("|")) && !(password.contains("&")) &&
                !(password.contains("'")) && !(password.contains("!")) && !(password.contains(";")));
    }

    /**
     * Metodo que comprueba si el nombre es correcto.
     *
     * @param name: nombre a comprobar.
     * @return true si el nombre es correcto.
     */
    public static boolean isValidName(@NotNull final String name) {
        return isQueryOk(name);
    }
}
