java_binary(
    name = "json_test",
    main_class= "com.vaticle.typedb.example.genealogy.math_genealogy.json_test",
    srcs=["json-test.java"],
    deps = ["@maven//:org_sharegov_mjson","@vaticle_typedb_client_java//:client-java",
                                                                "@vaticle_typedb_client_java//api",
                                                              "@vaticle_typeql//java:typeql-lang",
                                                              "@vaticle_typeql//java/query"],
    data =
        [
            ":data.json",
            ":datalite.json"
        ]
)
java_test(
    name="DataLoaderTest",
    test_class="com.vaticle.typedb.example.genealogy.math_genealogy.DataLoaderTest",
    srcs=["DataLoaderTest.java"],
    deps = ["@maven//:org_sharegov_mjson","@vaticle_typedb_client_java//:client-java",
                                                                           "@vaticle_typedb_client_java//api",
                                                                         "@vaticle_typeql//java:typeql-lang",
                                                                         "@vaticle_typeql//java/query", ":json_test"]
)
