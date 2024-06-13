package com.estimationengine.constants;

public interface BasicConstants {

    interface SQLQueries {
        public String GET_ALL_APP_SIZE = "SELECT e FROM AppSize e";
        public String GET_APP_EFFORT_BY_SIZE_AND_SCOPE = "SELECT ast FROM AppSizeScopeTBL ast\n" +
                " INNER JOIN ast.scope s\n" +
                " INNER JOIN ast.appSize a\n" +
                " WHERE s.scope=:scope AND a.appSize=:size";
        public String SAVE_CUSTOMER_SCOPE = "INSERT INTO customer_scope (customer_id,scope_id) VALUES (?,?)";
    }

    interface CalculationConstants {
        public int NO_OF_WEEKS_IN_A_SCRUM = 2;
        public int WORKING_HOURS = 8;
        public int WORKING_DAYS_IN_WEEK = 5;
        public int WEEKS_IN_A_YEAR = 52;
    }

    interface FileConstants{
        public String INPUT_FILE_TEMPLATE="template/Estimation_UI_template.pptx";
    }

    interface ErrorMessages{
        public String DOCUMENT_ERROR="Something went wrong while creating document";
        public String SOMETHING_WENT_WRONG="Something went wrong";
    }
}
