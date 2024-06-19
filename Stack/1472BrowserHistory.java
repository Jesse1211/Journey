package Stack;

import java.util.Stack;

class BrowserHistory {
    Stack<String> backward;
    Stack<String> forward;
    public BrowserHistory(String homepage) {
        this.backward = new Stack<String>();
        this.forward = new Stack<String>();
        this.backward.push(homepage);
    }
    
    public void visit(String url) {
        forward.clear();
        backward.push(url);
    }
    
    public String back(int steps) {
        while(steps-- > 0 && backward.size() > 1)//at least 1 web page mus be present for whomem we are finding the forward history 
        {
             forward.push(backward.pop());//storing the history for forward operation after
        }
        return backward.peek();//this web page forward history is made 
    }
    
    public String forward(int steps) {
        while(steps-- > 0 && !forward.isEmpty())
        {
             backward.push(forward.pop());//popping from history till a particular limit 
        }
        return backward.peek();//and the new current web page 
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */