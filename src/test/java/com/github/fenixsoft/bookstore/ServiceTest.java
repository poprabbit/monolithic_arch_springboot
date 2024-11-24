package com.github.fenixsoft.bookstore;

import com.github.fenixsoft.bookstore.domain.warehouse.StockpileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

public class ServiceTest extends DBRollbackBase {

    @Autowired
    private StockpileService stockpileService;

    @Test
    void frozen() {
        CompletableFuture.runAsync(() -> stockpileService.frozen(1, 2))
                .exceptionally(ex -> {
                    // 乐观锁异常
                    ex.printStackTrace();
                    return null;
        });
        stockpileService.frozen(1, 1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
