package com.cloudnets.cloudacademic.Database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Creado por Deimer Villa on 07/07/2015.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil{

    public static void main(String[] args)throws IOException, SQLException{
        writeConfigFile("ormlite_config.txt");
    }

}
