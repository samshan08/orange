package org.orange.dynamic.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by SAM on 2017/2/12.
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"},
features = "classpath:features/dynamic",
glue = "org.orange.dynamic.steps")
public class TestDynamicMessage
{
}
