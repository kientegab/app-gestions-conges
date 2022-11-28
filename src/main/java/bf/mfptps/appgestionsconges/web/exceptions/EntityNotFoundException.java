/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web.exceptions;

/**
 *
 * @author HEBIE
 */
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4273846781242800961L;

    public EntityNotFoundException(String message, Object... params) {
        super(String.format(message, params));
    }

}
