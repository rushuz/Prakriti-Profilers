import { useState } from "react";
import Login from "./components/Login";
import Questionnaire from "./components/Questionnaire";
import Result from "./components/Result";
import "./App.css";

function App() {
  const [step, setStep] = useState("login");
  const [result, setResult] = useState(null);

  if (step === "login") {
    return <Login onLoginSuccess={() => setStep("quiz")} />;
  }

  if (step === "quiz") {
    return <Questionnaire onFinish={(data) => {
      setResult(data);
      setStep("result");
    }} />;
  }

  return <Result data={result} />;
}

export default App;
