package examples;

import static def.react.react.Globals.createElement;
import static jsweet.dom.Globals.document;
import static jsweet.util.Globals.$map;
import static jsweet.util.Globals.any;
import static jsweet.util.Globals.array;
import static jsweet.util.Globals.union;

import def.react.jsx.Element;
import def.react.react.Component;
import def.react.react.ComponentClass;
import def.react.react.HTMLAttributes;
import def.react.react.ReactDOM;
import def.react.react.ReactElement;
import jsweet.lang.Interface;
import jsweet.lang.Optional;

public class SimpleExample3 {

	static final HTMLAttributes EMPTY = null;

	@SuppressWarnings("unchecked")
	public static <P, S, C extends Component<P, S>> ReactElement<P> createElementFromClass(Class<C> componentClass, P props) {
		return createElement((ComponentClass<P>) any(componentClass), props);
	}
	
	@Interface
	static abstract class Contact {
		int key;
		String name;
		@Optional
		String email;
		@Optional
		String description;
	}
	
	static class ContactItem extends Component<Contact, Object> {
		
		@Override
		public Element render() {
			Contact contact = union(props);
			return any(createElement("li", EMPTY, //
					createElement("h2", EMPTY, contact.name), //
					createElement("a", (HTMLAttributes) $map("href", "mailto:" + contact.email), contact.email), //
					createElement("h3", EMPTY, contact.description)) //
			);
		}
	}
	
	public static void main(String[] args) {
		Contact[] contacts = { new Contact() {
			{
				key = 1;
				name = "James Nelson";
				email = "james@jamesknelson.com";
				description = "this is a test";
			}
		}, new Contact() {
			{
				key = 2;
				name = "Bob";
				description = "hello";
			}
		}, new Contact() {
			{
				key = 3;
				name = "Renaud Pawlak";
				email = "rp@rp.com";
				description = "another test";
			}
		}, new Contact() {
			{
				key = 4;
				name = "John Smith";
				email = "JS@hello.com";
				description = "yet another description";
			}
		} };

		Object[] listElements = array(array(contacts).filter((contact, __, ___) -> {
			return contact.email != null;
		})).map((contact, __, ___) -> {
			return createElementFromClass(ContactItem.class, contact);
		});

		ReactElement<?> rootElement = createElement("div", (HTMLAttributes) $map(), //
				createElement("h1", EMPTY, "Contacts"), //
				createElement("ul", EMPTY, listElements));

		ReactDOM.render(rootElement, document.getElementById("react-app"));
	}

}

