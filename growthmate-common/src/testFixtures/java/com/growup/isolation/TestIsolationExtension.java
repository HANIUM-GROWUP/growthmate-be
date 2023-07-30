package com.growup.isolation;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

class TestIsolationExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        DatabaseCleaner databaseCleaner = getWannaflyDatabaseManager(context);
        databaseCleaner.truncateTables();
    }

    private DatabaseCleaner getWannaflyDatabaseManager(ExtensionContext context) {
        return (DatabaseCleaner) SpringExtension
                .getApplicationContext(context)
                .getBean("databaseCleaner");
    }
}
