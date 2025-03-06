package org.micdrop.highscore;

//This class exist solely to allow orphan removal in H2 persistence units
public class CustomH2Dialect extends org.hibernate.dialect.H2Dialect {
    @Override
    public String getDropForeignKeyString() {
        // Override if needed to customize the drop statement.
        return " drop constraint ";
    }

    @Override
    public String getAddForeignKeyConstraintString(String constraintName,
                                                   String[] foreignKey, String referencedTable,
                                                   String[] primaryKey, boolean referencesPrimaryKey) {
        // Append "on delete cascade" to the generated foreign key constraint
        String constraint = super.getAddForeignKeyConstraintString(constraintName, foreignKey, referencedTable, primaryKey, referencesPrimaryKey);
        return constraint + " on delete cascade";
    }
}