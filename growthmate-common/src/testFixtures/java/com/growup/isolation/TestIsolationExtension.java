package com.growup.isolation;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

class TestIsolationExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        DatabaseCleaner databaseCleaner = getDatabaseManager(context);
        databaseCleaner.truncateTables();
    }

    private DatabaseCleaner getDatabaseManager(ExtensionContext context) {
        return (DatabaseCleaner) SpringExtension
                .getApplicationContext(context)
                .getBean("databaseCleaner");
    }
}
