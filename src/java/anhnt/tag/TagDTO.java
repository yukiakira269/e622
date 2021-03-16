/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.tag;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class TagDTO implements Serializable{
    
    private int tagId;
    private String Description;

    public TagDTO() {
    }

    public TagDTO(int tagId, String Description) {
        this.tagId = tagId;
        this.Description = Description;
    }

    /**
     * @return the tagId
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * @param tagId the tagId to set
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
    
}
