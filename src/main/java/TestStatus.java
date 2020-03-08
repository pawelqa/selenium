import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestStatus implements AfterTestExecutionCallback {
    public boolean testIsFailed;

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            testIsFailed = true;
        } else testIsFailed = false;
    }
}
