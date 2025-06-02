import React, { useState, useEffect, useRef } from "react";
import ReactDOM from "react-dom/client";

// Main App Component
function App() {
    const [temp, setTemp] = useState(null);
    const [history, setHistory] = useState([]);
    const canvasRef = useRef(null);

    useEffect(() => {
        fetch("https://your-backend-api.com/temperature")
            .then((response) => response.json())
            .then((data) => {
                setTemp(data.currentTemp);
                setHistory(data.history || []);
                drawGraph(data.history || []);
            });
    }, []);

    function drawGraph(data) {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        ctx.beginPath();
        ctx.strokeStyle = "blue";
        ctx.lineWidth = 2;
        data.forEach((value, index) => {
            ctx.lineTo(index * 10, canvas.height - value * 2);
        });
        ctx.stroke();
    }

    return (
        <div style={{ textAlign: "center", fontFamily: "Arial" }}>
            <h1>Temperature: {temp !== null ? `${temp}°C` : "Loading..."}</h1>
            <canvas ref={canvasRef} width="300" height="150" style={{ border: "1px solid black" }}></canvas>
        </div>
    );
}

// Render App
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);
