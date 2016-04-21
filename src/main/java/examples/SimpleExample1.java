package examples;

import static def.react.react.Globals.createElement;
import static jsweet.dom.Globals.document;

import def.react.react.HTMLAttributes;
import def.react.react.ReactDOM;
import def.react.react.ReactElement;

public class SimpleExample1 {

	static final HTMLAttributes EMPTY = null;

	public static void main(String[] args) {
		ReactElement<?> rootElement = createElement("div", EMPTY, //
				createElement("h1", EMPTY, "Contacts"), //
				createElement("ul", EMPTY, //
						createElement("li", EMPTY, //
								createElement("h2", EMPTY, "James Nelson"), //
								createElement("a", new HTMLAttributes() {
									{
										href = "mailto:james@jamesknelson.com";
									}
								}, "james@jamesknelson.com")), //
						createElement("li", EMPTY, //
								createElement("h2", EMPTY, "Joe Citizen"), //
								createElement("a", new HTMLAttributes() {
									{
										href = "mailto:joe@example.com";
									}
								}, "joe@example.com"))));

		ReactDOM.render(rootElement, document.getElementById("react-app"));
	}

}

