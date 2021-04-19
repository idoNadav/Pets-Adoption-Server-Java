package com.petsadoption.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbServiceFactory {

    private InMemoryDbService inMemoryDbService;
    private SqlDbService sqlDbService;

    @Value("${pas.db.service.method}")
    private String dbMethod;

    @Autowired
    public DbServiceFactory(InMemoryDbService inMemoryDbService, SqlDbService sqlDbService) {
        this.inMemoryDbService = inMemoryDbService;
        this.sqlDbService = sqlDbService;
    }


    IDbService getDbService() {
        if (dbMethod.equalsIgnoreCase("sql")) {
            return sqlDbService;
        }
        return inMemoryDbService;
    }
}
