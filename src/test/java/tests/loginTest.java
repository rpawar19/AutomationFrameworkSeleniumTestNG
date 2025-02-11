package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class loginTest extends baseClass {
    @Test
    public void test1(){
        loginpage.openClientSite();
        Assert.assertTrue(false);
    }
}
