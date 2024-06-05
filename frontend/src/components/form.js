import React, { useState, useEffect } from 'react';

const SERVER_URL = 'http://localhost:8080';

const Form = () => {
    const [textData, setTextData] = useState(null);
    const [error, setError] = useState(null);
    const [selectedFile, setSelectedFile] = useState(null);

    const gettingTextInfo = async () => {
        try {
            const formData = new FormData();
            formData.append('file', selectedFile);

            const response = await fetch(SERVER_URL, {
                method: 'POST',
                body: formData,
            });

            if (response.ok) {
                const data = await response.json();
                setTextData(data);
                setError(null);
            } else {
                setError('Ошибка обработки файла');
            }
        } catch (error) {
            setError('Ошибка получения данных: ' + error.message);
        }
    };

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const handleButtonClick = () => {
        if (!selectedFile) {
            setError('Выберите файл');
            return;
        }
        setTextData(null);
        gettingTextInfo();
    };

    return (
        <div>
            <input style={{fontSize: '25px'}} type="file" accept=".txt" onChange={handleFileChange}/>
            <br/>
            <br/>
            <button style={{fontSize: '25px'}} onClick={handleButtonClick}>
                Узнать
            </button>
            <br/>
            <br/>
            {error && <div>Ошибка: {error}</div>}
            {textData && (
                <table style={{textAlign: 'center', margin: '0 auto', border: '1px solid black'}}>
                    <thead>
                    <tr>
                        <th style={{border: '1px solid black'}}></th>
                        <th style={{border: '1px solid black'}}>Слово</th>
                        <th style={{border: '1px solid black'}}>Количество в тексте</th>
                    </tr>
                    </thead>
                    <tbody>
                    {Object.entries(textData).map(([word, count], index) => (
                        <tr key={word}>
                            <td style={{border: '1px solid black'}}>{index + 1}</td>
                            <td style={{border: '1px solid black'}}>{word}</td>
                            <td style={{border: '1px solid black'}}>{count}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default Form;