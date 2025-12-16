import recommendations from "./recommendations";

function Result({ data }) {
  const rec = recommendations[data.result];

  return (
    <div>
      <h2>Prakriti Evaluation Result</h2>

      <p><strong>Vata:</strong> {data.vata}%</p>
      <p><strong>Pitta:</strong> {data.pitta}%</p>
      <p><strong>Kapha:</strong> {data.kapha}%</p>

      <h3>Dominant Prakriti: {data.result}</h3>

      <hr />

      <h3>Recommended Diet</h3>
      <ul>
        {rec.diet.map((item, i) => (
          <li key={i}>{item}</li>
        ))}
      </ul>

      <h3>Lifestyle Suggestions</h3>
      <ul>
        {rec.lifestyle.map((item, i) => (
          <li key={i}>{item}</li>
        ))}
      </ul>

      <h3>Exercise Recommendations</h3>
      <ul>
        {rec.exercise.map((item, i) => (
          <li key={i}>{item}</li>
        ))}
      </ul>
    </div>
  );
}

export default Result;
