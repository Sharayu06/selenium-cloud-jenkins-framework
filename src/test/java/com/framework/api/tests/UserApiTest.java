package com.framework.api.tests;

import com.framework.api.base.ApiBase;
import com.framework.api.clients.UserClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends ApiBase {

    @Test
    public void getUsersTest() {

        Response response = UserClient.getUsers();

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.statusCode(), 200);

        int size = response.jsonPath().getList("$").size();

        Assert.assertTrue(size > 0);

        System.out.println("Total users = " + size);
    }
}
