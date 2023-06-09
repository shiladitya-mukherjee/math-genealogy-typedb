java_binary(
    name = "DataMigrator",
    main_class= "math_genealogy.DataMigrator",
    srcs=["DataMigrator.java"],
    deps =
        [
                "@maven//:org_sharegov_mjson","@vaticle_typedb_client_java//:client-java",
                "@vaticle_typedb_client_java//api",
                "@vaticle_typeql//java:typeql-lang",
                "@vaticle_typeql//java/query"
        ],
    data =
        [
            ":data.json",
            ":datalite.json"
        ]
)
java_test(
    name="data-loader-test",
    test_class="math_genealogy.DataLoaderTest",
    srcs=["DataLoaderTest.java"],
    deps =
        [
            "@maven//:org_sharegov_mjson","@vaticle_typedb_client_java//:client-java",
            "@vaticle_typedb_client_java//api",
            "@vaticle_typeql//java:typeql-lang",
            "@vaticle_typeql//java/query", ":DataMigrator"
        ]
)
