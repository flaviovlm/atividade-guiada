const API_URL = 'http://localhost:8080/calculadora';

const sunIcon = `<svg viewBox="0 0 24 24"><circle cx="12" cy="12" r="5"/><path d="M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42"/></svg>`;
const moonIcon = `<svg viewBox="0 0 24 24"><path d="M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z"/></svg>`;

const themeToggleBtn = document.getElementById('theme-toggle');
themeToggleBtn.innerHTML = moonIcon;

themeToggleBtn.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');
    themeToggleBtn.innerHTML = document.body.classList.contains('dark-mode') ? sunIcon : moonIcon;
});

const inputNum1 = document.getElementById('number1');
const inputNum2 = document.getElementById('number2');
const spanResultado = document.getElementById('resultado');
const historyList = document.getElementById('history-list');
const opButtons = document.querySelectorAll('.op-btn');
const btnCalcular = document.getElementById('btn-calcular');

let operacaoSelecionada = 'Soma';

const simbolos = {
    'Soma': '+', 'Subtração': '-', 'Multiplicação': '×', 
    'Divisão': '÷', 'Porcentagem': '%', 'Potenciação': 'x²', 'Raiz quadrada': '√'
};

function formatarOperacao(op) {
    const simbolo = simbolos[op.tipoCalculo] || '?';
    if (op.tipoCalculo === 'Raiz quadrada') return `√${op.number1} = ${op.resultado}`;
    return `${op.number1} ${simbolo} ${op.number2} = ${op.resultado}`;
}

async function carregarHistorico() {
    try {
        const response = await fetch(API_URL);
        const operacoes = await response.json();
        historyList.innerHTML = '';
        operacoes.forEach(op => {
            const li = document.createElement('li');
            li.innerHTML = `<span>${formatarOperacao(op)}</span><button class="btn-delete" onclick="deletarOperacao(${op.id})">×</button>`;
            historyList.appendChild(li);
        });
    } catch (e) { console.error(e); }
}

async function deletarOperacao(id) {
    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    carregarHistorico();
}

// LÓGICA DE SELEÇÃO DE OPERAÇÃO
opButtons.forEach(btn => {
    btn.addEventListener('click', () => {
        // Remove seleção anterior
        opButtons.forEach(b => b.classList.remove('active'));
        // Seleciona a atual
        btn.classList.add('active');
        operacaoSelecionada = btn.getAttribute('data-op');

        // Esconde ou mostra o segundo input dependendo da operação
        if (operacaoSelecionada === 'Raiz quadrada') {
            inputNum2.style.display = 'none';
            inputNum1.placeholder = "Digite o número";
            inputNum2.value = ''; // Limpa o valor do input 2
        } else {
            inputNum2.style.display = 'block';
            inputNum1.placeholder = "Nº 1";
        }
    });
});

// LÓGICA DE CÁLCULO
btnCalcular.addEventListener('click', async () => {
    const num1 = parseFloat(inputNum1.value);
    const num2 = parseFloat(inputNum2.value) || 0;

    if (isNaN(num1)) {
        spanResultado.innerText = "Nº 1 vazio";
        return;
    }

    const textoOriginal = btnCalcular.innerText;
    btnCalcular.innerText = "Calculando...";

    try {
        const res = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
                number1: num1, 
                number2: num2, 
                tipoCalculo: operacaoSelecionada
            })
        });
        
        const data = await res.json();
        spanResultado.innerText = data.resultado ?? "Erro";
        carregarHistorico();
    } catch (e) { 
        spanResultado.innerText = "ERRO"; 
    } finally {
        btnCalcular.innerText = textoOriginal;
    }
});

carregarHistorico();