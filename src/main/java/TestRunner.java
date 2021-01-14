import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;

@CucumberOptions (
        features = "src/test/java/features",
        glue = {"steps"},
        plugin = {"pretty:STDOUT", "com.cucumber.listener.ExtentCucumberFormatter:ExtentReports/cucumber-extent/report.html"},
        monochrome = true,
        strict = true
)
public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
            public void feature(CucumberFeatureWrapper cucumberFeature) throws Throwable{
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass
    public void writeExtentReport() {
        Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/main/resources/extent-config.xml"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setTestRunnerOutput("Extent Report");
        testNGCucumberRunner.finish();
    }
}
