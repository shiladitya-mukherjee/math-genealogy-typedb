package com.vaticle.typedb.example.genealogy.math_genealogy;

import com.vaticle.typedb.client.api.TypeDBClient;
import com.vaticle.typedb.client.api.TypeDBOptions;
import com.vaticle.typedb.client.api.TypeDBSession;
import com.vaticle.typedb.client.TypeDB;
import com.vaticle.typedb.client.api.TypeDBTransaction;
import com.vaticle.typedb.client.api.answer.Numeric;
import com.vaticle.typedb.client.api.query.QueryFuture;
import com.vaticle.typeql.lang.TypeQL;
import com.vaticle.typeql.lang.query.TypeQLMatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import static com.vaticle.typeql.lang.TypeQL.var;
import static org.junit.Assert.assertEquals;


public class DataLoaderTest
{
    TypeDBClient client;
    TypeDBSession session;
    TypeDBOptions options;
    String databaseName = "onboarding";
    String databaseAddress = "localhost:1729";

    @Before
    public void loadDataAndConnect() throws FileNotFoundException
    {
        json_test.main(new String[] {});
        client = TypeDB.coreClient(databaseAddress);
        session = client.session(databaseName, TypeDBSession.Type.DATA);
        options=TypeDBOptions.core().infer(true);
    }

    @Test
    public void assertMigrationResults()
    {
        try(TypeDBTransaction transaction = session.transaction(TypeDBTransaction.Type.READ, options))
        {

            //139 mathematician
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("mathematician")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 139);
            }

            //139 id
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("id")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 139);
            }
            //10 speciality
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("speciality")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 10);
            }
            //139 mastery
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("mastery")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 139);
            }
            //18 research
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("research")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 18);
            }
            //20 offer
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("offer")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 20);
            }
            //160 name
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("name")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 160);
            }
            //10 school
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("school")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 10);
            }
            //44 year of degree
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("year_of_degree")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 44);
            }
            //11 subject
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("subject")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 11);
            }
            //2 co-teach
            {
                TypeQLMatch.Unfiltered.Aggregate query=TypeQL.match(var("x").isa("co-teach")).count();
                QueryFuture<Numeric> ans=transaction.query().match(query);
                assertEquals(ans.get().asLong(), 2);
            }
        }
    }

    @After
    public void disconnect()
    {
        session.close();
        client.close();
    }
}