/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.product;

/**
 *
 * @author DELL
 */
public class CreateBookError {
    private String descLengthError;
    private String emptyFieldsError;
    private String idNotFoundError;
    private String numberFormatError;

    public CreateBookError() {
    }

    public CreateBookError(String descLengthError, String emptyFieldsError, String idNotFoundError, String numberFormatError) {
        this.descLengthError = descLengthError;
        this.emptyFieldsError = emptyFieldsError;
        this.idNotFoundError = idNotFoundError;
        this.numberFormatError = numberFormatError;
    }

    /**
     * @return the descLengthError
     */
    public String getDescLengthError() {
        return descLengthError;
    }

    /**
     * @param descLengthError the descLengthError to set
     */
    public void setDescLengthError(String descLengthError) {
        this.descLengthError = descLengthError;
    }

    /**
     * @return the emptyFieldsError
     */
    public String getEmptyFieldsError() {
        return emptyFieldsError;
    }

    /**
     * @param emptyFieldsError the emptyFieldsError to set
     */
    public void setEmptyFieldsError(String emptyFieldsError) {
        this.emptyFieldsError = emptyFieldsError;
    }

    /**
     * @return the idNotFoundError
     */
    public String getIdNotFoundError() {
        return idNotFoundError;
    }

    /**
     * @param idNotFoundError the idNotFoundError to set
     */
    public void setIdNotFoundError(String idNotFoundError) {
        this.idNotFoundError = idNotFoundError;
    }

    /**
     * @return the numberFormatError
     */
    public String getNumberFormatError() {
        return numberFormatError;
    }

    /**
     * @param numberFormatError the numberFormatError to set
     */
    public void setNumberFormatError(String numberFormatError) {
        this.numberFormatError = numberFormatError;
    }

    
    
}
