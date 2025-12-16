import { useState } from "react";
import axios from "axios";

const questions = [
  {
    text: "What is your body build?",
    options: [
      { label: "Thin and lean", dosha: "VATA" },
      { label: "Medium and muscular", dosha: "PITTA" },
      { label: "Broad and heavy", dosha: "KAPHA" }
    ]
  },
  {
    text: "How is your appetite?",
    options: [
      { label: "Irregular, sometimes low", dosha: "VATA" },
      { label: "Strong and frequent", dosha: "PITTA" },
      { label: "Slow but steady", dosha: "KAPHA" }
    ]
  },
  {
    text: "How is your digestion?",
    options: [
      { label: "Unpredictable, gas or bloating", dosha: "VATA" },
      { label: "Fast digestion", dosha: "PITTA" },
      { label: "Slow digestion", dosha: "KAPHA" }
    ]
  },
  {
    text: "What best describes your skin?",
    options: [
      { label: "Dry and rough", dosha: "VATA" },
      { label: "Warm and sensitive", dosha: "PITTA" },
      { label: "Smooth and oily", dosha: "KAPHA" }
    ]
  },
  {
    text: "How do you usually feel emotionally?",
    options: [
      { label: "Anxious or worried", dosha: "VATA" },
      { label: "Irritable or intense", dosha: "PITTA" },
      { label: "Calm and stable", dosha: "KAPHA" }
    ]
  },
  {
    text: "How is your sleep pattern?",
    options: [
      { label: "Light and disturbed", dosha: "VATA" },
      { label: "Moderate but sometimes interrupted", dosha: "PITTA" },
      { label: "Deep and long", dosha: "KAPHA" }
    ]
  },
  {
    text: "How do you handle stress?",
    options: [
      { label: "Overthinking", dosha: "VATA" },
      { label: "Anger or frustration", dosha: "PITTA" },
      { label: "Withdrawal or laziness", dosha: "KAPHA" }
    ]
  },
  {
    text: "How is your energy level during the day?",
    options: [
      { label: "Fluctuates a lot", dosha: "VATA" },
      { label: "High but can burn out", dosha: "PITTA" },
      { label: "Steady but slow", dosha: "KAPHA" }
    ]
  },
  {
    text: "What is your body build?",
    options: [
      { label: "Thin and lean", dosha: "VATA" },
      { label: "Medium and muscular", dosha: "PITTA" },
      { label: "Broad and heavy", dosha: "KAPHA" }
    ]
  },
  {
    text: "How is your appetite?",
    options: [
      { label: "Irregular, sometimes low", dosha: "VATA" },
      { label: "Strong and frequent", dosha: "PITTA" },
      { label: "Slow but steady", dosha: "KAPHA" }
    ]
  },
  {
    text: "How is your digestion?",
    options: [
      { label: "Unpredictable, gas or bloating", dosha: "VATA" },
      { label: "Fast digestion", dosha: "PITTA" },
      { label: "Slow digestion", dosha: "KAPHA" }
    ]
  },
  {
    text: "What best describes your skin?",
    options: [
      { label: "Dry and rough", dosha: "VATA" },
      { label: "Warm and sensitive", dosha: "PITTA" },
      { label: "Smooth and oily", dosha: "KAPHA" }
    ]
  },
  {
    text: "How do you usually feel emotionally?",
    options: [
      { label: "Anxious or worried", dosha: "VATA" },
      { label: "Irritable or intense", dosha: "PITTA" },
      { label: "Calm and stable", dosha: "KAPHA" }
    ]
  },
  {
    text: "How is your sleep pattern?",
    options: [
      { label: "Light and disturbed", dosha: "VATA" },
      { label: "Moderate but sometimes interrupted", dosha: "PITTA" },
      { label: "Deep and long", dosha: "KAPHA" }
    ]
  },
  {
    text: "How do you handle stress?",
    options: [
      { label: "Overthinking", dosha: "VATA" },
      { label: "Anger or frustration", dosha: "PITTA" },
      { label: "Withdrawal or laziness", dosha: "KAPHA" }
    ]
  },
  {
    text: "How is your energy level during the day?",
    options: [
      { label: "Fluctuates a lot", dosha: "VATA" },
      { label: "High but can burn out", dosha: "PITTA" },
      { label: "Steady but slow", dosha: "KAPHA" }
    ]
  }
];

function Questionnaire({ onFinish }) {
  const [current, setCurrent] = useState(0);
  const [answers, setAnswers] = useState([]);
  const [selected, setSelected] = useState(null);

  const selectOption = (dosha) => {
    setSelected(dosha);
  };

  const next = async () => {
    const updatedAnswers = [...answers];
    updatedAnswers[current] = selected;
    setAnswers(updatedAnswers);
    setSelected(null);

    if (current < questions.length - 1) {
      setCurrent(current + 1);
    } else {
      const res = await axios.post(
        "http://localhost:8080/evaluate",
        { answers: updatedAnswers }
      );
      onFinish(res.data);
    }
  };

  const q = questions[current];

  return (
    <div>
      <h2>
        Question {current + 1} of {questions.length}
      </h2>

      <p>{q.text}</p>

      {q.options.map((opt, idx) => (
        <button
          key={idx}
          onClick={() => selectOption(opt.dosha)}
          style={{
            display: "block",
            margin: "8px 0",
            padding: "8px",
            backgroundColor:
              selected === opt.dosha ? "#a5d6a7" : "#f0f0f0",
            border: "1px solid #ccc",
            cursor: "pointer"
          }}
        >
          {opt.label}
        </button>
      ))}

      <br />

      <button
        onClick={next}
        disabled={!selected}
        style={{
          padding: "8px 16px",
          cursor: selected ? "pointer" : "not-allowed"
        }}
      >
        {current === questions.length - 1 ? "Submit" : "Next"}
      </button>
    </div>
  );
}

export default Questionnaire;
