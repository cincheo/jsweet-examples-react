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
import def.react.react.FormEvent;
import def.react.react.HTMLAttributes;
import def.react.react.ReactDOM;
import def.react.react.ReactElement;
import jsweet.lang.Date;
import jsweet.lang.Interface;
import jsweet.lang.Optional;

public class SimpleExampleTodoApp {

	static final HTMLAttributes EMPTY = null;
	
	@SuppressWarnings("unchecked")
	public static <P, S, C extends Component<P, S>> ReactElement<P> createElementFromClass(Class<C> componentClass, P props) {
		return createElement((ComponentClass<P>) any(componentClass), props);
	}
	
	public static void main(String[] args) {
		ReactDOM.render(createElementFromClass(TodoApp.class, (Object) null), document.getElementById("react-app"));
	}

	@Interface
	static class TodoListProps {
		TodoProps[] items;
	}

	@Interface
	static class TodoProps {
		double id;
		String text;
	}

	@Interface
	static class TodoAppState {
		@Optional
		TodoProps[] items;
		String text;
	}

	static class TodoList2 extends Component<TodoListProps, Object> {

		private Element createItem(TodoProps item, Double __, TodoProps[] ___) {
			return any(createElement("li", (HTMLAttributes) $map("key", item.id), item.text));
		}

		public Element render() {
			TodoListProps props = union(this.props);
			return any(createElement("ul", EMPTY, array(array(props.items).map(this::createItem))));
		}
	}

	static class TodoApp extends Component<Object, TodoAppState> {

		TodoAppState state = new TodoAppState() {
			{
				items = new TodoProps[0];
				text = "";
			}
		};

		public void onChange(FormEvent e) {
			this.setState(new TodoAppState() {
				{
					text = (String) e.target.$get("value");
				}
			});
		}

		public void handleSubmit(FormEvent e) {
			e.preventDefault();
			TodoProps[] nextItems = array(state.items).concat(new TodoProps() {
				{
					text = state.text;
					id = Date.now();
				}
			});
			String nextText = "";
			this.setState(new TodoAppState() {
				{
					items = nextItems;
					text = nextText;
				}
			});
		}

		public Element render() {
			return any(createElement("div", EMPTY, //
					createElement("h3", EMPTY, "TODO"), //
					SimpleExampleTodoApp.createElementFromClass(TodoList2.class, new TodoListProps() {
						{
							items = state.items;
						}
					}), //
					createElement("form", new HTMLAttributes() {
						{
							onSubmit = TodoApp.this::handleSubmit;
						}
					}, //
							createElement("input", new HTMLAttributes() {
								{
									onChange = TodoApp.this::onChange;
									value = union(state.text);
								}
							}), //
							createElement("button", EMPTY, "Add #" + (this.state.items.length + 1)))));
		}
	}
	
}

