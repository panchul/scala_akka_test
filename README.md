# scala_akka_test

A template for scala specs2 testing.

Uses ```Generators```:

    val thingieGen: Gen[(Int, Int)] = Gen.oneOf(Seq((1, 2)))


Here is an example of how it works. Tests are ran of two types. Both are ran with command:

    $ sbt test
    [info] Loading global plugins from /.../.sbt/0.13/plugins
    [info] Loading project definition from /.../scala_akka_test/project
    [info] Set current project to thingieProcessor (in build file:/.../scala_akka_test/)
    [TRACE] TestKit: sent "hi" to Actor[akka://default/user/test-actor#-868101444]
    [TRACE] TestKit: sent "hi" to Actor[akka://default/user/test-actor#-8715569]
    [TRACE] DemoActor: got "hi" 
    [TRACE] DemoActor: got "hi" 
    [TRACE] DemoActor: sender is "Actor[akka://default/system/testActor-2#-68734473]" 
    [TRACE] DemoActor: sender is "Actor[akka://default/system/testActor-1#-1975290378]" 
    [TRACE] TestKit: got "hi dear" 
    [TRACE] TestKit: got "hi dear" 
    [TRACE from myfunc()] Gen TestKit: demo is Actor[akka://ThingieProcessor2Specs/user/$a#-1086236194]
    [TRACE from myfunc()] Gen TestKit: sent "hi" to Actor[akka://ThingieProcessor2Specs/user/$a#-1086236194]
    [TRACE] DemoActor: got "hi" 
    [TRACE from myfunc()] Gen TestKit: expecting "hi dear"... 
    [TRACE] DemoActor: sender is "Actor[akka://ThingieProcessor2Specs/system/testActor-4#1053352887]" 
    [TRACE from myfunc()] Gen TestKit: after expectMsg 
    [TRACE from myfunc()] Gen TestKit: demo is Actor[akka://ThingieProcessor2Specs/user/$b#-1929109388]
    [TRACE from myfunc()] Gen TestKit: sent "bye" to Actor[akka://ThingieProcessor2Specs/user/$b#-1929109388]
    [TRACE from myfunc()] Gen TestKit: expecting "bye dear"... 
    [TRACE] DemoActor: got "bye" 
    [TRACE] DemoActor: sender is "Actor[akka://ThingieProcessor2Specs/system/testActor-4#1053352887]" 
    [TRACE from myfunc()] Gen TestKit: after expectMsg 
    [TRACE from myfunc()] Gen TestKit: demo is Actor[akka://ThingieProcessor2Specs/user/$c#884175126]
    [TRACE from myfunc()] Gen TestKit: sent "bye" to Actor[akka://ThingieProcessor2Specs/user/$c#884175126]
    [TRACE] DemoActor: got "bye" 
    [TRACE from myfunc()] Gen TestKit: expecting "bye dear"... 
    [TRACE] DemoActor: sender is "Actor[akka://ThingieProcessor2Specs/system/testActor-4#1053352887]" 
    [TRACE from myfunc()] Gen TestKit: after expectMsg 
    [TRACE from myfunc()] Gen TestKit: demo is Actor[akka://ThingieProcessor2Specs/user/$d#-1185539908]
    [TRACE] DemoActor: got "bye" 
    [TRACE from myfunc()] Gen TestKit: sent "bye" to Actor[akka://ThingieProcessor2Specs/user/$d#-1185539908]
    [TRACE] DemoActor: sender is "Actor[akka://ThingieProcessor2Specs/system/testActor-4#1053352887]" 
    [TRACE from myfunc()] Gen TestKit: expecting "bye dear"... 
    [TRACE from myfunc()] Gen TestKit: after expectMsg 
    [TRACE from myfunc()] Gen TestKit: demo is Actor[akka://ThingieProcessor2Specs/user/$e#990953577]
    [TRACE from myfunc()] Gen TestKit: sent "hi" to Actor[akka://ThingieProcessor2Specs/user/$e#990953577]
    ...
    [TRACE] DemoActor: sender is "Actor[akka://ThingieProcessor2Specs/system/testActor-4#1053352887]" 
    [TRACE from myfunc()] Gen TestKit: after expectMsg 
    [TRACE from myfunc()] Gen TestKit: demo is Actor[akka://ThingieProcessor2Specs/user/$Ib#1113745833]
    [TRACE from myfunc()] Gen TestKit: sent "bye" to Actor[akka://ThingieProcessor2Specs/user/$Ib#1113745833]
    [TRACE from myfunc()] Gen TestKit: expecting "bye dear"... 
    [TRACE] DemoActor: got "bye" 
    [TRACE] DemoActor: sender is "Actor[akka://ThingieProcessor2Specs/system/testActor-4#1053352887]" 
    [TRACE from myfunc()] Gen TestKit: after expectMsg 
    [TRACE from myfunc()] Gen TestKit: demo is Actor[akka://ThingieProcessor2Specs/user/$Jb#-1894735105]
    [TRACE from myfunc()] Gen TestKit: sent "hi" to Actor[akka://ThingieProcessor2Specs/user/$Jb#-1894735105]
    [TRACE from myfunc()] Gen TestKit: expecting "hi dear"... 
    [TRACE] DemoActor: got "hi" 
    [TRACE] DemoActor: sender is "Actor[akka://ThingieProcessor2Specs/system/testActor-4#1053352887]" 
    [TRACE from myfunc()] Gen TestKit: after expectMsg 
    [info] TestkitSpecification
    [info] 
    [info] ThingieProcessor should
    [info]   + Test with hardcoded values using TestkitSpecification
    [info] 
    [info] 
    [info] Total for specification TestkitSpecification
    [info] Finished in 19 ms
    [info] 1 example, 0 failure, 0 error
    [info] 
    [info] ThingieProcessorSpecification
    [info]        + Test thingie using Gens
    [info] 
    [info] 
    [info] Total for specification ThingieProcessorSpecification
    [info] Finished in 75 ms
    [info] 1 example, 0 failure, 0 error
    [info] 
    [info] ThingieProcessor2Specification
    [info]   + Test with Generators using ThingieProcessor2Specification
    [info] 
    [info] Total for specification ThingieProcessor2Specification
    [info] Finished in 201 ms
    [info] 1 example, 100 expectations, 0 failure, 0 error
    [info] 
    [info] TestkitWithGenSpecification
    [info] 
    [info] ThingieProcessor should
    [info]   + Test with hardcoded values in TestkitWithGenSpecification
    [info] 
    [info] 
    [info] Total for specification TestkitWithGenSpecification
    [info] Finished in 17 ms
    [info] 1 example, 0 failure, 0 error
    [info] 
    [info] Passed: Total 4, Failed 0, Errors 0, Passed 4
    [success] Total time: 6 s, completed Aug 13, 2017 11:00:31 AM

    
    