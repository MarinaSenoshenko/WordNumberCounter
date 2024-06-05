import React from 'react';
import Info from "./components/info";
import Form from "./components/form";


class App extends React.Component {
    render() {
        return (
            <div style={{ fontSize: '25px', textAlign: "center"}}>
                <Info />
                <Form />
            </div>
        );
    }
}

export default App;