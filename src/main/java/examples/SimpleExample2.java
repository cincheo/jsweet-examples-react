package examples;

import static def.react.react.Globals.createElement;
import static jsweet.dom.Globals.document;
import static jsweet.util.Globals.$map;
import static jsweet.util.Globals.array;

import def.react.react.HTMLAttributes;
import def.react.react.ReactDOM;
import def.react.react.ReactElement;
import jsweet.lang.Interface;
import jsweet.lang.Optional;

public class SimpleExample2 {

	static final HTMLAttributes EMPTY = null;

	@Interface
	static abstract class Contact {
		int key;
		String name;
		@Optional
		String email;
	}

	public static void main(String[] args) {
		Contact[] contacts = { new Contact() {
			{
				key = 1;
				name = "James Nelson";
				email = "james@jamesknelson.com";
			}
		}, new Contact() {
			{
				key = 2;
				name = "Bob";
			}
		}, new Contact() {
			{
				key = 3;
				name = "Renaud Pawlak";
				email = "rp@rp.com";
			}
		}, new Contact() {
			{
				key = 4;
				name = "John Smith";
				email = "JS@hello.com";
			}
		} };

		Object[] listElements = array(array(contacts).filter((contact, __, ___) -> {
			return contact.email != null;
		})).map((contact, __, ___) -> {
			return createElement("li", (HTMLAttributes) $map("key", contact.key), //
					createElement("h2", EMPTY, contact.name), //
					createElement("a", new HTMLAttributes() {
				{
					href = "mailto:" + contact.email;
				}
			}, contact.email));
		});

		ReactElement<?> rootElement = createElement("div", (HTMLAttributes) $map(), //
				createElement("h1", EMPTY, "Contacts"), //
				createElement("ul", EMPTY, listElements));

		ReactDOM.render(rootElement, document.getElementById("react-app"));
	}

}
