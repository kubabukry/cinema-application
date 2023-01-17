import './App.css';

function App() {
  const reqBody = {
    login: "radek",
    password: "zaq1@WSX",
  };

  fetch("/persons/auth/authenticate", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "post",
    body: JSON.stringify(reqBody),
  })
    .then((response) => Promise.all([response.json(), response.headers]))
    .then(([body]) => console.log(body.token));
  
  return (
    <div className="App">
  
    </div>
  );
}

export default App;
