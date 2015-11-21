# ensure

Java library for checking assertions during runtime. In comparison to Guava's Preconditions and the Validate from commons-lang ensure offers more detailed test methods and where possible, the value checked is directly returned.

By using the assertions in any code, Bugs may be found earlier and with more informative error messages.

Some examples for the possible checks:

    public void foobar(String message, List<String> topics, Object obj) {
       this.message = ensureNotEmpty(message, "message must not be empty");
       String firstTopic = ensureNotEmpty(topics, "topics for message %s must not be empty", message).get(0);
       Topic topic = ensureInstanceOf(Topic.class, obj, "obj must be a Topic");
    }
