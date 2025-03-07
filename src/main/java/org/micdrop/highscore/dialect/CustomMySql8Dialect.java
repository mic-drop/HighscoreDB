package org.micdrop.highscore.dialect;

import org.hibernate.dialect.MySQL8Dialect;

public class CustomMySql8Dialect extends MySQL8Dialect {


    @Override
    public String getAddForeignKeyConstraintString(String constraintName,
                                                   String[] foreignKey, String referencedTable,
                                                   String[] primaryKey, boolean referencesPrimaryKey) {
        // Get the standard foreign key constraint string...
        String constraint = super.getAddForeignKeyConstraintString(constraintName, foreignKey, referencedTable, primaryKey, referencesPrimaryKey);
        // Append ON DELETE CASCADE
        return constraint + " ON DELETE CASCADE";
    }
}

