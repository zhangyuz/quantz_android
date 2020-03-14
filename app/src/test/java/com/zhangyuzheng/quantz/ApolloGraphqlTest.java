package com.zhangyuzheng.quantz;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.junit.Test;

import okhttp3.OkHttpClient;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ApolloGraphqlTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    private ApolloCall.Callback<IndexDailyQuery.Data> indexDailyCallback = new ApolloCall.Callback<IndexDailyQuery.Data>() {
        @Override
        public void onResponse(Response<IndexDailyQuery.Data> response) {
            System.out.println("onResponse:" + response.data());
        }

        @Override
        public void onFailure(ApolloException e) {
            System.out.println("onFailure:" + e);
        }
    };
    @Test
    public void graphql_query_index_daily() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("Http://127.0.0.1:5000/graphql")
                .okHttpClient(okHttpClient)
                .build();
        final  IndexDailyQuery indexDailyQuery = IndexDailyQuery.builder()
                .tsCode("000001.SH").first(10).build();
        ApolloCall<IndexDailyQuery.Data> indexDailyCall = apolloClient.query(indexDailyQuery);
        indexDailyCall.enqueue(indexDailyCallback);
        try {
            // Wait for graphql query to finish
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            System.out.println("" + e);
        }
    }
}