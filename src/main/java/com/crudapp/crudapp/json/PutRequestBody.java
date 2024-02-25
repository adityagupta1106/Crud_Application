package com.crudapp.crudapp.json;

import lombok.Data;

@Data
public class PutRequestBody {
    public Long userId;
    FieldsToBeUpdated fieldsToBeUpdated;

}

