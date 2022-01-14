using API_PTUD_R5.Model;
using API_PTUD_R5.Service;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace API_PTUD_R5.Controller
{
    [Route("api/sanpham")]
    [ApiController]
    public class SANPHAMController : ControllerBase
    {
        private readonly SANPHAMService _bookService;

        public SANPHAMController(SANPHAMService bookService)
        {
            _bookService = bookService;
        }

        [HttpGet]
        public ActionResult<List<SANPHAM>> Get()
        {
            System.Diagnostics.Debug.WriteLine("tring");
            return _bookService.Get();

        }


        [HttpGet("{id}")]
        public ActionResult<SANPHAM> Get(int id)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            return book;
        }

        [HttpGet("filter/{maphanloai}")]
        public List<SANPHAM> filterTheoPhanLoai(long maphanloai)
        {
            var book = _bookService.filter(maphanloai);
            return book;
        }

        [HttpGet("shop/{manhacungcap}")]
        public List<SANPHAM> filterTheoNCC(long manhacungcap)
        {
            var book = _bookService.shop(manhacungcap);
            return book;
        }

        [HttpGet("timkiem")]
        public List<SANPHAM> TimKiem([FromQuery] string tensp)
        {
            var book = _bookService.TimKiem(tensp);
            return book;
        }

        [HttpPost]
        public ActionResult<SANPHAM> Create(SANPHAM book)
        {
            SANPHAM temp = _bookService.Create(book);

            if (temp != null)
                return Ok(book);
            return BadRequest();
        }

        [HttpPut("{id}")]
        public IActionResult Update(int id, SANPHAM bookIn)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            _bookService.Update(id, bookIn);

            return Ok(bookIn);
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(long id)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            _bookService.Remove(book.Id);

            return NoContent();
        }
    }
}
