package math_genealogy;

import com.vaticle.typedb.client.api.TypeDBClient;
import com.vaticle.typedb.client.api.TypeDBOptions;
import com.vaticle.typedb.client.api.TypeDBSession;
import com.vaticle.typedb.client.TypeDB;
import com.vaticle.typedb.client.api.TypeDBTransaction;
import com.vaticle.typedb.client.api.answer.Numeric;
import com.vaticle.typedb.client.api.answer.NumericGroup;
import com.vaticle.typedb.client.api.query.QueryFuture;
import com.vaticle.typeql.lang.TypeQL;
import com.vaticle.typeql.lang.query.TypeQLMatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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
        DataMigrator.main(new String[] {});
        client = TypeDB.coreClient(databaseAddress);
        session = client.session(databaseName, TypeDBSession.Type.DATA);
        options=TypeDBOptions.core().infer(true);
    }

    @Test
    public void assertMigrationResults()
    {
        try(TypeDBTransaction transaction = session.transaction(TypeDBTransaction.Type.READ, options))
        {
            Map<String, Long> expected=new HashMap<>();
            expected.put("Mathematician", Long.valueOf(139));
            expected.put("id", Long.valueOf(139));
            expected.put("speciality", Long.valueOf(10));
            expected.put("mastery", Long.valueOf(139));
            expected.put("research", Long.valueOf(18));
            expected.put("offer", Long.valueOf(20));
            expected.put("name", Long.valueOf(160));
            expected.put("school", Long.valueOf(10));
            expected.put("year_of_degree", Long.valueOf(44));
            expected.put("subject", Long.valueOf(1));
            expected.put("co-teach", Long.valueOf(2));


            TypeQLMatch.Unfiltered.Group.Aggregate query = TypeQL.match(
                    var("x").isaX(var("y"))
            ).get("x", "y").group("y").count();
            Stream<NumericGroup> query_stream=transaction.query().match(query);
            query_stream.forEach(element->{String label=element.owner().asType().getLabel().name(); if (expected.containsKey(label)) assertEquals(expected.get(label).longValue(), element.numeric().asLong());});
        }
    }

    @After
    public void disconnect()
    {
        session.close();
        client.close();
    }
}
