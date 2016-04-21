package examples;

import static def.react.react.Globals.createElement;
import static jsweet.dom.Globals.clearInterval;
import static jsweet.dom.Globals.document;
import static jsweet.dom.Globals.setInterval;
import static jsweet.util.Globals.any;
import static jsweet.util.Globals.function;

import def.react.jsx.Element;
import def.react.react.Component;
import def.react.react.ComponentClass;
import def.react.react.DOMAttributes;
import def.react.react.ReactDOM;
import def.react.react.ReactElement;
import jsweet.lang.Interface;

public class SimpleExampleTimer {

	@SuppressWarnings("unchecked")
	public static <P, S, C extends Component<P, S>> ReactElement<P> createElementFromClass(Class<C> componentClass, P props) {
		return createElement((ComponentClass<P>) any(componentClass), props);
	}
	
	public static void main(String[] args) {
		ReactDOM.render(createElementFromClass(Timer.class, (Object) null), document.getElementById("react-app"));		
	}

	@Interface
	static class TimerState {
		int secondsElapsed;
	}

	static class Timer extends Component<Object, TimerState> {
		String displayName = "Timer";
		int interval;

		TimerState state = new TimerState() {
			{
				secondsElapsed = 0;
			}
		};

		public void tick() {
			this.setState(new TimerState() {
				{
					secondsElapsed = state.secondsElapsed + 1;
				}
			});
		}

		public void componentDidMount() {
			interval = any(setInterval(function(this::tick), 1000));
		}

		public void componentWillUnmount() {
			clearInterval(interval);
		}

		public Element render() {
			return any(createElement("div", (DOMAttributes) null, "Seconds Elapsed: ", this.state.secondsElapsed));
		}
	}

	
}

