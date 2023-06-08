java_binary(
    name="hello_world",
    main_class = "com.vaticle.typedb.example.genealogy.math_genealogy.hello_world",
    srcs=["hello_world.java"],
    deps = ["@vaticle_typedb_client_java//:client-java",
                      "@vaticle_typedb_client_java//api",
                    "@vaticle_typeql//java:typeql-lang",
                    "@vaticle_typeql//java/query",
                    "@maven//:com_google_code_gson_gson",
                    "@maven//:org_sharegov_mjson"
            ],
    data =
    [
        ":json-test.json"
    ],
)

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
